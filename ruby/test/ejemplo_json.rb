require 'json'
require_relative './helpers'

def suppress_output
  original_stdout, original_stderr = $stdout.clone, $stderr.clone
  $stderr.reopen File.new('/dev/null', 'w')
  $stdout.reopen File.new('/dev/null', 'w')
  yield
ensure
  $stdout.reopen original_stdout
  $stderr.reopen original_stderr
end

resultados = nil

suppress_output do
  resultados = TADsPec.testear
end

PrinterJson.new.imprimir_tadspec(resultados)

