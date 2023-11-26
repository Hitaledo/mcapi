# McApi
Minecraft web api for interacting with Spigot server
# Getting Started
Install the plugin on your server.
Configure the plugin by editing the config.yml file:
```yml
Config:
  port: 80
  password: password
```
# Usage
Execute commands remotely using a simple HTTP POST request. Here's an example using cURL:
```bash
curl -X POST http://serverip/command -d "command=console command" -d "password=server password"
```
# Contributing
We welcome contributions from the community. If you have ideas or want to contribute, please feel free to open an issue or submit a pull request.
