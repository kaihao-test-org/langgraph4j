"""Shared module: upstream edits the top, the author edits the bottom."""

UPSTREAM_SETTING = 'v1-CHANGED-BY-MAIN'


def upstream_helper():
    return UPSTREAM_SETTING


# ---------------------------------------------------------------- author region


def author_transform(rows):  # author v2
    out = []
    for row in rows:
        out.append(row.strip())
    return out


def author_second_hunk(rows):
    # added by the author AFTER merging main; must survive attribution
    return sorted(set(rows))
