package bacta.service.scheduler;

import bacta.lang.Observer;
import bacta.lang.Subject;

import java.util.concurrent.Future;

/**
 * Created by Kyle on 9/5/2014.
 */
public abstract class Task<EventType> implements Runnable, Observer<EventType> {

    protected Future<?> future = null;

    @Override
    public final void setSubject(Subject sub) {

    }

    protected void setFuture(Future<?> future) {
        this.future = future;
    }

    public boolean cancel(boolean canInterrupt) {
        return future.cancel(canInterrupt);
    }
}
