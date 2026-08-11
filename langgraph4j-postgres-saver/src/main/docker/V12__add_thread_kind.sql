-- Thread kinds: caller-supplied kind markers for a thread.

CREATE TABLE LG4JThreadKind (
    kind_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_kind_thread FOREIGN KEY(thread_id) REFERENCES LG4JThread(thread_id) ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadkind_thread_id ON LG4JThreadKind(thread_id);
CREATE UNIQUE INDEX idx_unique_lg4jthreadkind_slug ON LG4JThreadKind(thread_id, slug);
