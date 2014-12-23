# lein-karma

A very simple Leiningen plugin to execute [Karma test runner](http://karma-runner.github.io).

Intended to be used with Karma installed from
[NPM](https://www.npmjs.com/)
package manager.

Pairs well with the [lein-npm](https://github.com/RyanMcG/lein-npm)
plugin. See [Example](#Example)

## Usage

Put `[lein-karma-test "0.1.0"]` into the `:plugins` vector of your project.clj.

Generate a Karma config file:

    $ lein karma init

Start Karma:

    $ lein karma start

Provide CLI arguments:

    $ lein karma start my-karma.conf.js --singleRun

View Karma's available commands:

    $ lein karma -- --help

## Example

In `project.clj`:

```clojure
:plugins [[lein-npm "0.4.0"]
          [lein-karma-test "0.1.0"]

:node-dependencies [[karma "0.12.28"]
                    [karma-chrome-launcher "0.1.7"]]
```


    $ lein npm install
    $ lein karma init
    $ lein karma start

## License

Copyright © 2014 Logan Linn

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
