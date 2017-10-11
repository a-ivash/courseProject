package project.model.users.comparators;

import project.model.users.Subscriber;

import java.util.Comparator;

/** This comparator compares 2 Subscriber instances in the following way:
 *  1. Not activated subscriber always goes first
 *  2. If their active statuses are equal - compare their ID
 * @author Oleksii Ivashchenko
 * @version 1.0
 * */
public class SubscriberActiveComparator implements Comparator<Subscriber> {
    @Override
    public int compare(Subscriber o1, Subscriber o2) {
        if (o1.isActive() == o2.isActive()) {
            return (int) (o1.getId() - o2.getId());
        }
        if (!o1.isActive() && o2.isActive()) {
            return -1;
        }
        return 1;
    }
}
