-- Thread groups: lets callers organise threads into named groups.

CREATE TABLE LG4JThreadGroup (
    group_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_group_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadgroup_thread_id ON LG4JThreadGroup(thread_id);

CREATE UNIQUE INDEX idx_unique_lg4jthreadgroup_slug ON LG4JThreadGroup(thread_id, name);
