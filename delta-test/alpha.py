"""Alpha module for delta-diff testing."""

UPSTREAM_TOUCHED = True  # changed by main


def load_config(path):
    with open(path) as handle:
        return handle.read()


def compute(values):  # v3
    total = 0
    for value in values:
        total += value
    return total / len(values)
