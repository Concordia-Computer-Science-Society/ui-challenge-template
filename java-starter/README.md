# Java Starter (Swing)

A plain, working sign up form with a bunvh of different parts to ruin: text fields,
a slider, a dropdown, radio buttons, a checkbox, buttons, a menu bar and a status bar.

## Files
| File | What it is |
|---|---|
| `src/Main.java` | The form. Start here. Look for `>>> MESS WITH THIS` comments for ideas. |
| `src/Example.java` | A running away buttons. Won't run until you call it from `Main.java`. |

## Run it
From inside `java-starter/`:

```bash
javac -d out src/*.java
java -cp out Main
```

Or open the folder in your IDE (IntelliJ, Eclipse, VS Code, BlueJ) and run `Main.java`.

Requires Java 11 or newer. No extra libraries needed. Swing is built in.

## Try this first
In `Main.java`, find the line that creates the Submit button and add this right after it:

```java
Examples.makeRunAway(submitButton);
```

Run it and try to click Submit. Now you know how the toolbox works.

## Tips
- Keep your entry point in `Main.java` so judges can run it the same way.
- Put any new classes in `src/`.
- Don't commit `out/` or `.class` files. The `.gitignore` handles this.
- Bad is the goal. Impossible is not. Make sure a patient person can still submit the form.
- no malware ffs
- no nsfw you sicko...


