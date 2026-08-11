-- Thread notes: freeform notes attached to a thread.

CREATE TABLE LG4JThreadNote (
    note_id UUID PRIMARY KEY,
    thread_id UUID NOT NULL,
    body TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_note_thread FOREIGN KEY(thread_id) REFERENCES LG4JThread(thread_id) ON DELETE CASCADE
);

CREATE INDEX idx_lg4jthreadnote_thread_id ON LG4JThreadNote(thread_id);
