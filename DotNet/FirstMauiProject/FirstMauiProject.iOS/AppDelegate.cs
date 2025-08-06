using Foundation;

namespace FirstMauiProject.iOS;

[Register(nameof(AppDelegate))]
public class AppDelegate : MauiUIApplicationDelegate
{
	protected override MauiApp CreateMauiApp() => MauiProgram.CreateMauiApp();


	private void Hi()
	{
		NSUrlSession.SharedSession.CreateDataTask(new NSUrl("https://www.google.com"), (data, response, error) =>
		{
			{
				var dataString = NSString.FromData(data, NSStringEncoding.UTF8);
				Console.WriteLine(dataString);
			}
		}).Resume();
	}
}
