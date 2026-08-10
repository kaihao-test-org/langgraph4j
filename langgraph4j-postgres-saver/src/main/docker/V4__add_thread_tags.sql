-- Thread tags: caller-supplied tags for grouping threads.

CREATE TABLE LG4JThreadTag (
    tag_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tag_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadtag_thread_id ON LG4JThreadTag(thread_id);

CREATE UNIQUE INDEX idx_unique_lg4jthreadtag_slug ON LG4JThreadTag(thread_id, name);

-- touch
