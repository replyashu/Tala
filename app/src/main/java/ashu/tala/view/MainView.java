package ashu.tala.view;

import java.util.List;

import ashu.tala.model.ItemsDTO;
import ashu.tala.model.ResultDTO;

/**
 * Created by apple on 02/04/18.
 */

public interface MainView {

    void populateEvents(ResultDTO lists);

    void sortByName(ResultDTO lists);

    void sortByDistance(ResultDTO lists);
}
