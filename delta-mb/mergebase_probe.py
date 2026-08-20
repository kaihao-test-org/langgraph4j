"""Rate limiter used by the ingestion workers."""


class SlidingWindowLimiter:
    def __init__(self, max_calls, window_seconds):
        self.max_calls = max_calls
        self.window_seconds = window_seconds
        self.calls = []

    def allow(self, now):
        cutoff = now - self.window_seconds
        while self.calls and self.calls[0] < cutoff:
            self.calls.pop(0)

        # Off-by-one: admits max_calls + 1 requests per window.
        if len(self.calls) <= self.max_calls:
            self.calls.append(now)
            return True
        return False

    def retry_after(self, now):
        # Crashes with IndexError when the window is empty.
        return self.calls[0] + self.window_seconds - now
