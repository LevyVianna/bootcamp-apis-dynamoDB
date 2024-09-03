package bootcamp.user_crud_api.repository;

import bootcamp.user_crud_api.model.User;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {

    private final DynamoDBMapper mapper;

    public UserRepository(AmazonDynamoDB dynamoDB) {
        this.mapper = new DynamoDBMapper(dynamoDB);
    }

    public Optional<User> findById(String username) {
        User user =  mapper.load(User.class, username);
        return Optional.ofNullable(user);
    }

    public boolean existsById(String username) {
        User user = mapper.load(User.class, username);
        return user != null;
    }

    public void deleteById(String username) {
        User userToDelete = mapper.load(User.class, username);
        if (userToDelete != null) {
            mapper.delete(userToDelete);
        }
    }

    public User save(User user) {
        mapper.save(user);
        return user;
    }

    public List<User> findByEmail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName("EmailIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(eav);

        return mapper.query(User.class, queryExpression);
    }
}
