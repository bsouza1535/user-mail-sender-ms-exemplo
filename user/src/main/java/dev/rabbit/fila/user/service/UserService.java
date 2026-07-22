package dev.rabbit.fila.user.service;
import dev.rabbit.fila.user.domain.UserModel;
import dev.rabbit.fila.user.dto.UserDto;
import dev.rabbit.fila.user.producer.UserProducer;
import dev.rabbit.fila.user.repositorie.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import dev.rabbit.fila.user.exception.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    /**
     * Retrieves all users from the database.
     * This method queries the UserRepository to fetch and return a list of all UserModel entities.
     *
     * @return a list containing all users in the database
     */
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Saves the given UserModel to the database and then publishes an event with the saved user.
     * This method is transactional, ensuring that the event is only published if the user is successfully saved.
     * First, it persists the user using the UserRepository, then it triggers the UserProducer to publish the event.
     *
     * @param userModel the user entity to be saved and published
     * @return the persisted UserModel
     */
    @Transactional
    public UserModel saveAndPublish (UserModel userModel) {
        userModel = userRepository.save(userModel);
        userProducer.publishEvent(userModel);
        return userModel;
    }

    @Transactional
    public UserModel getUserById(UUID userId) {
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID " + userId + " not found"));

        return userModel;
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User with ID " + userId + "not found");
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public UserDto updateUserById(UUID userId, UserDto userDto) {
        UserModel userModel = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User with ID " + userId + " not found."));


        BeanUtils.copyProperties(userDto, userModel);

        UserModel updated = userRepository.save(userModel);

        return new UserDto(
                updated.getName(),
                updated.getEmail()
        );
    }
}
