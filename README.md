# Java Code Injection Example


Injects custom code into a running java process.


## Commands

```sh
# compile everything
javac sample/*.java

# build agent.jar
jar -cvmf MANIFEST.MF agent.jar sample/Agent.class

# run target process in another terminal (prints the PID)
java sample/Target

# run injector
java sample/Injector <PID> [agent0|agent1|agent2]
```


## Goals (a.k.a. Agents)
- [x] `agent0`: Prints something into the target process.
- [ ] `agent1`: Calls a private function from the target process.
- [ ] `agent2`: Executes custom code from a Minecraft process.


## Devcontainer
I hate managing multiple java versions, so I added a `.devcontainer` config in case you want to use the same environment as me.

https://code.visualstudio.com/docs/devcontainers/containers


## Sources
- [What is a Java Agent](https://youtu.be/ShSjzru4kZA)
