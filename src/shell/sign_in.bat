rem echo|set /p=%date% %time%; >> "C:\sing_in_track\pointage.csv"
for /f "tokens=2 delims==" %%I in ('wmic os get lastbootuptime /format:list') do set datetime=%%I
rem echo %datetime%
set mydate=%datetime:~6,2%/%datetime:~4,2%/%datetime:~0,4% %datetime:~8,2%:%datetime:~10,2%:%datetime:~12,2%,%datetime:~15,2%
rem echo %mydate%
echo|set /p=%mydate%; >> "C:\sing_in_track\pointage.csv"