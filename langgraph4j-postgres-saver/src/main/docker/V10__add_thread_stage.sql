-- Thread stages: caller-supplied stage markers for a thread.

CREATE TABLE LG4JThreadStage (
    stage_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_stage_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadstage_thread_id ON LG4JThreadStage(thread_id);

CREATE UNIQUE INDEX idx_unique_lg4jthreadstage_slug ON LG4JThreadStage(thread_id, slug);
