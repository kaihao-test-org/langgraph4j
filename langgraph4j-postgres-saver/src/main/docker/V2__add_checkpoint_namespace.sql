-- Namespaced checkpoints: allows a single thread to hold several independent
-- checkpoint lineages, addressed by a caller-supplied namespace.

CREATE TABLE LG4JCheckpointNamespace (
    namespace_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(255) NOT NULL,
    is_default BOOLEAN DEFAULT FALSE NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_namespace_thread
        FOREIGN KEY(thread_id)
        REFERENCES LG4JThread(thread_id)
        ON DELETE CASCADE
);

CREATE INDEX idx_lg4jcheckpointnamespace_thread_id ON LG4JCheckpointNamespace(thread_id);

CREATE UNIQUE INDEX idx_unique_lg4jcheckpointnamespace_slug ON LG4JCheckpointNamespace(thread_id, slug);
