package pl.revanmj.qtassist;

import android.app.Dialog;
import android.app.SearchManager;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

public class AssistantTileService extends TileService {

    @Override
    public void onStartListening() {
        super.onStartListening();

        Tile tile = getQsTile();
        tile.setState(Tile.STATE_INACTIVE);
        tile.updateTile();
    }

    @Override
    public void onClick() {
        super.onClick();

        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        try {
            // Below part is needed so that quick tiles tray gets closed
            // and a screenshot passed to the digital assistant contains screen underneath the tray.
            Dialog dialog = new Dialog(this);
            showDialog(dialog);
            dialog.dismiss();
            Thread.sleep(500);

            SearchManager.class.getMethod("launchAssist", Bundle.class).invoke(searchManager, new Bundle());
        } catch(NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                InterruptedException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
