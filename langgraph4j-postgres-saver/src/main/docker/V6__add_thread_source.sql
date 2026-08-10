-- Thread source: records which subsystem created a thread.

CREATE TABLE LG4JThreadSource (
    source_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    subsystem VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_source_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadsource_thread_id ON LG4JThreadSource(thread_id);
