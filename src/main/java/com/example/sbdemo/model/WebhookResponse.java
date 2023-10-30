package com.example.sbdemo.model;

import com.example.sbdemo.utils.JsonBinaryType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chartink_webhook_response")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class WebhookResponse {
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "pk_id", nullable = false)
    @Id
    private UUID id;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb",name = "response")
    private Object response;

    @Column(name="created_datetime")
    private LocalDateTime created;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
