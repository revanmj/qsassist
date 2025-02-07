package pl.revanmj.qtassist;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

public class SettingsTileService extends TileService {

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

        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startActivityAndCollapse(PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE));
        } else {
            startActivityAndCollapse(intent);
        }
    }

}
