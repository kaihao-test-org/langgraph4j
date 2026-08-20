"""Helper added in a follow-up commit; intentionally clean."""


def clamp(value, low, high):
    if low > high:
        raise ValueError("low must not exceed high")
    return max(low, min(value, high))
