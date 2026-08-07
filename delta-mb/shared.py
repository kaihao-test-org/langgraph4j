"""Shared module: upstream edits the top, the author edits the bottom."""

UPSTREAM_SETTING = 'v1-CHANGED-BY-MAIN'


def upstream_helper():
    return UPSTREAM_SETTING


# ---------------------------------------------------------------- author region


def author_transform(rows):  # author v1
    out = []
    for row in rows:
        out.append(row.strip())
    return out
