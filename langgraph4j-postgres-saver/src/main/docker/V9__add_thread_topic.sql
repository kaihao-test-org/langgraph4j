-- Thread topics: caller-supplied topics for grouping threads.

CREATE TABLE LG4JThreadTopic (
    topic_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_topic_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadtopic_thread_id ON LG4JThreadTopic(thread_id);

CREATE UNIQUE INDEX idx_unique_lg4jthreadtopic_slug ON LG4JThreadTopic(thread_id, name);
