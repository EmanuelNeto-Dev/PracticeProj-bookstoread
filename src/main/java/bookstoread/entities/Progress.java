package bookstoread.entities;

public class Progress {

    private final int completed;
    private final int toRead;
    private final int inProgress;

    public Progress(int completed, int toRead, int inProgress) {
        this.completed = completed;
        this.toRead = toRead;
        this.inProgress = inProgress;
    }

    public int percentualCompleted() {
        return completed;
    }

    public int percentualToRead() {
        return toRead;
    }

    public int percentualInProgress() {
        return inProgress;
    }

}
