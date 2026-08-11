-- Thread labels: caller-supplied labels for grouping threads.

CREATE TABLE LG4JThreadLabelV8 (
    label_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_label_v8_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadlabelv8_thread_id ON LG4JThreadLabelV8(thread_id);

CREATE UNIQUE INDEX idx_unique_lg4jthreadlabelv8_slug ON LG4JThreadLabelV8(thread_id, slug);
