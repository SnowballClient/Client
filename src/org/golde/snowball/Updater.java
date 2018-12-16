package org.golde.snowball;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Updater
{

	public enum UpdateResult
	{
		NO_UPDATE,
		FAIL,
		UPDATE_AVAILABLE,
		DEV;
	}

	public class UpdateResults
	{
		private UpdateResult result;
		private String version;

		public UpdateResults(UpdateResult result, String version)
		{
			this.result = result;
			this.version = version;
		}

		public UpdateResult getResult()
		{
			return result;
		}

		public String getVersion()
		{
			return version;
		}
	}

	private final String urlBase = "https://raw.githubusercontent.com/SnowballClient/files/master/downloads/version.txt";
	private String currentVersion;

	public UpdateResults checkForUpdates()
	{

		currentVersion = Snowball.instance.VERSION;
		
		try
		{
			HttpURLConnection con = (HttpURLConnection) new URL(urlBase).openConnection();

			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");

			if (con.getResponseCode() == 500)
				return new UpdateResults(UpdateResult.FAIL, "Server responded with code 500: Internal server error");

			String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();

			con.disconnect();

			switch(compareVersion(version)){
			case 0: return new UpdateResults(UpdateResult.NO_UPDATE, null);
			case -1: return new UpdateResults(UpdateResult.UPDATE_AVAILABLE, version);
			case 1: return new UpdateResults(UpdateResult.DEV, version);
			default: return new UpdateResults(UpdateResult.FAIL, "This was not suppost to happen!");
			}

		}
		catch (Exception ex)
		{
			return new UpdateResults(UpdateResult.FAIL, ex.toString());
		}


	}

	private int compareVersion(String newVersion)
	{
		System.out.println("CurrVer: " + currentVersion);
		System.out.println("NewVer: " + newVersion);
		
		Scanner currentV = new Scanner(currentVersion);
		Scanner newV = new Scanner(newVersion);

		currentV.useDelimiter("\\.");
		newV.useDelimiter("\\.");

		while(currentV.hasNextInt() && newV.hasNextInt())
		{
			int cV = currentV.nextInt();
			int nV = newV.nextInt();

			if(cV < nV)
			{
				currentV.close();
				newV.close();

				return -1;
			}
			else if(cV > nV)
			{
				currentV.close();
				newV.close();

				return 1;
			}
		}

		if(currentV.hasNextInt())
		{
			currentV.close();
			newV.close();

			return 1;
		}

		if(newV.hasNextInt())
		{
			currentV.close();
			newV.close();

			return -1;
		}

		currentV.close();
		newV.close();
		return 0;
	}
}
