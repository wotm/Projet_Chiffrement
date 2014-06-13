package iut.montreuil.projet.tuteure.easycrypt.modele;

import iut.montreuil.projet.tuteure.easycrypt.R;
import iut.montreuil.projet.tuteure.easycrypt.modele.TacheChiffrement.TypeTache;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetLanceur extends AppWidgetProvider {

	private static final String WIDGET_BUTTON = "action_start_service_test";
	public static boolean ChiffrementEnCours = false;
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		super.onUpdate(context, appWidgetManager, appWidgetIds);

		
		for(int i =0; i< appWidgetIds.length; i++ ){
			int appWidgetId = appWidgetIds[i];
			
			Intent intent = new Intent(context, WidgetLanceur.class);
			intent.setAction(WIDGET_BUTTON);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
					intent, 0);

			
			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
			views.setOnClickPendingIntent(R.id.button1, pendingIntent);

			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
		

		System.out.println("Widget installée");

	}

	
	
	@Override
	public void onReceive(Context context, Intent intent) {

		super.onReceive(context, intent);
		System.out.println("dans receive() :"+intent.getAction());
		
		
		if (WIDGET_BUTTON.equals(intent.getAction())) {
			if(!ChiffrementEnCours) {
				ChiffrementEnCours = true;
				System.out.println("Je lance le service !");
				
				TacheChiffrement t = new TacheChiffrement(context, TypeTache.ByWidget);
				t.execute(null,null,null);
			}else {
				System.out.println("Je ne lance pas le service car il est déja en cours de chiffrement !");
			}
		}

	}

}
