package rs.com.dashboardgems.firemuster;

import rs.com.dashboardgems.model.PersonData;

/**
 * Created by yasar on 19/8/17.
 */

public interface PassListInterface {

    void ascending();

    void descending();

    void defaultList();

    void searchlist(String search);

    void checkSelectState();

    void updateList();

    void addUpdateList(PersonData personData);
}
