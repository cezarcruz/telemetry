package br.com.bank.consumer.gateway.mongo;

import br.com.bank.consumer.gateway.mongo.entity.ConsumerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends MongoRepository<ConsumerEntity, String> {
}
