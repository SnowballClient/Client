@Echo off
rem delete src folder
rem make src folder
rem copy C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\Client\src\minecraft to C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src
rem make patch between C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src\minecraft\net\minecraft & C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientUnpatched\src\minecraft\net\minecraft
rem save patch to patch folder minecraft.patch
rem delete C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src\minecraft\net\minecraft
rem print finished

rmdir /s /q C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src
mkdir C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src
xcopy /s /e C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\Client\src\minecraft C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src
cd C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientUnpatched\src\minecraft\net\minecraft
C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\Tools\diff.exe -rc . C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src\net\minecraft > C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\patch\minecraft.patch
rmdir /s /q C:\Users\Eric\Documents\Games\Minecraft\SnowballClient\ClientGithub\src\net\minecraft
echo Finished
pause