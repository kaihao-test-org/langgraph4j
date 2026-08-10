-- Thread labels: free-form tags a caller can attach to a thread for grouping.

CREATE TABLE LG4JThreadLabel (
    label_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_label_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadlabel_thread_id ON LG4JThreadLabel(thread_id);

CREATE UNIQUE INDEX idx_unique_lg4jthreadlabel_slug ON LG4JThreadLabel(thread_id, name);
