# Assignment 3 | Bridge Pattern

Name: Yerkebulan Niyalov
Group: SE 2526
Topic: A - Drawing

## What it does

Circle stores radius 2, and Square stores side 3. Each shape delegates drawing
to a Renderer. Vector, raster and ASCII drawing are simulated using strings.
There is no GUI and no extra dependency. All classes use the default package.

## Role map

| Role | Class | Source |
| --- | --- | --- |
| Abstraction | Shape | `src/Shape.java` |
| A1 | Circle | `src/Circle.java` |
| A2 | Square | `src/Square.java` |
| Implementor | Renderer | `src/Renderer.java` |
| I1 | VectorRenderer | `src/VectorRenderer.java` |
| I2 | RasterRenderer | `src/RasterRenderer.java` |
| I3 | AsciiRenderer | `src/AsciiRenderer.java` |
| Client | Main | `src/Main.java` |

## Where to look

- `src/Shape.java`: `private Renderer renderer` is the bridge field.
  The constructor receives this interface reference; `getRenderer()` is protected.
- `src/Shape.java`: `public abstract String execute()` is the client operation.
- `src/Circle.java` and `src/Square.java`: `execute()` delegates through Renderer.
- `src/Shape.java`: `setImplementation(Renderer renderer)` replaces the reference.
- `src/Main.java`: `checkSwitch()` is T5. It saves `original`, compares
  `original == circle`, and compares the ID and radius before and after switching.
- `src/Main.java`: `check()` compares actual and expected strings and counts results.
- `report.pdf`, page 2: UML diagram. Page 3: five annotated code excerpts.

## Build and run

Install JDK 17. From this folder, run:

```sh
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected results

| Check | Classes | Expected result |
| --- | --- | --- |
| T1 | Circle + VectorRenderer | `VECTOR circle radius=2` |
| T2 | Circle + RasterRenderer | `RASTER circle radius=2` |
| T3 | Square + VectorRenderer | `VECTOR square side=3` |
| T4 | Square + RasterRenderer | `RASTER square side=3` |
| T5 | Circle + VectorRenderer -> RasterRenderer | `sameObject=true`, `idUnchanged=true`, `radiusUnchanged=true`; before: `VECTOR circle radius=2`; after: `RASTER circle radius=2` |
| T6 | Circle + AsciiRenderer | `ASCII circle radius=2 (o)` |
| T7 | Square + AsciiRenderer | `ASCII square side=3 [+]` |

Final line: `SUMMARY: 7/7 PASS`. See `demo-output.txt` for captured output.
A failed check prints FAIL and its expected result. The remaining checks still run.



