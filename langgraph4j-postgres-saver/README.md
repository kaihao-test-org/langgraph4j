# postgres-saver

Checkpoints are stored in the `checkpoints` table, keyed by thread id.

Thread ids are opaque strings; the saver does not parse them.
