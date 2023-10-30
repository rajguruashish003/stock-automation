package com.example.sbdemo.model.Repo;

import com.example.sbdemo.model.WebhookResponse;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface WebhookResponseRepo extends CrudRepository<WebhookResponse, UUID> {

}
