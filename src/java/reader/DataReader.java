package reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import model.SignIn;

public class DataReader
{
	public static enum Interval
	{
		DAY, WEEK, MONTH, YEAR;
	}

	private Set<SignIn> data = new LinkedHashSet<>();

	public DataReader(String filePath) throws IOException
	{
		if (filePath == null)
			throw new NullPointerException("There is no file path specified");
		List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
		fillData(lines);
	}

	private void fillData(List<String> lines)
	{
		for (String line : lines)
		{
			String[] dates = line.split(";");
			// normal case is when the size of the array is 2 : sign-in sign-out
			// some times when the pc crashes then the sign-in will be present
			// twice
			// and in other case when the user restart the pc manually, it can
			// happen that the we have duplicate data

			if (dates == null)
				continue;

			// the default format that is dd/MM/YYYY hh:mm:ss,SS; 16/07/2017
			// 16:43:14,55;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss,SS");

			try
			{
				Date signIn = dateFormat.parse(dates[0]);
				Date signOut = null;
				if (dates.length >= 2)
					signOut = dateFormat.parse(dates[dates.length - 1]);
				data.add(new SignIn(signIn, signOut));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
	}

	public Set<SignIn> getData(Interval interval)
	{
		return data;
	}
}
