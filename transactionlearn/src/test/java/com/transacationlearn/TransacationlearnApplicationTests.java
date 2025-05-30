package com.transacationlearn;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.transacationlearn.model.Learn;
import com.transacationlearn.repository.LearnRepository;
import com.transacationlearn.service.LearnService;

import jakarta.transaction.Transactional;

@SpringBootTest
class TransacationlearnApplicationTests {

	@Autowired
    private LearnService learnService;

    @Autowired
    private LearnRepository learnRepository;

    @Test
    public void testCreateLearn_Success() {
        // Arrange
        String topic = "Spring Boot";
        String description = "Testing createLearn() method";

        // Act
        Learn createdLearn = learnService.createLearn(topic, description);

        // Assert
        assertThat(createdLearn.getId()).isNotNull();
        assertThat(createdLearn.getTopic()).isEqualTo(topic);
        assertThat(createdLearn.getDescription()).isEqualTo(description);

        Optional<Learn> saved = learnRepository.findById(createdLearn.getId());
        assertThat(saved).isPresent();
        assertThat(saved.get().getTopic()).isEqualTo("Spring Boot");
    }

    @Test
    @Transactional  // Common in tests!
    public void testTransactionRollbackOnException() {
        long countBefore = learnRepository.count();

        try {
            // Simulate failure by calling service with null that may trigger exception
            learnService.createLearn(null, "Will not be saved");
        } catch (Exception e) {
            System.err.println(e);
        }

        long countAfter = learnRepository.count();
        assertThat(countAfter).isEqualTo(countBefore); // Ensure no data was persisted
    }

}
