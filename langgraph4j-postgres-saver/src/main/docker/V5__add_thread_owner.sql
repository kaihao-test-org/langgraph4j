-- Thread owner: records which principal created a thread.

CREATE TABLE LG4JThreadOwner (
    owner_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    principal VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_owner_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadowner_thread_id ON LG4JThreadOwner(thread_id);
