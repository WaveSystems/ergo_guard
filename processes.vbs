Set WshShell = WScript.CreateObject("WScript.Shell")
Set locator = CreateObject("WbemScripting.SWbemLocator")
Set service = locator.ConnectServer()
Set processes = service.ExecQuery _
 ("select name from Win32_Process")
For Each process in processes
wscript.echo process.Name 
Next
Set WSHShell = Nothing
