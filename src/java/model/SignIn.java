package model;

import java.util.Date;

import com.sun.istack.internal.Nullable;

public class SignIn
{
	private Date signIn;
	private Date signOut;

	public SignIn(Date signIn, @Nullable Date signOut)
	{
		this.signIn = signIn;
		this.signOut = signOut;
	}

	/**
	 * 
	 * @return duration in seconds.
	 */
	public double getDuration()
	{
		if (signIn != null && signOut != null && signOut.after(signIn))
		{
			double duration = signOut.getTime() - signIn.getTime();
			return duration / 1000d;
		}
		return -1;
	}

	public Date getSignIn()
	{
		return signIn;
	}

	public @Nullable Date getSignOut()
	{
		return signOut;
	}
}
