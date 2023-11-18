package notImplemented

import configuration.Configuration

object NotYetImplementedForThisTarget : NotImplementedErrorProvider(
	"This has not yet been implemented for the target '${Configuration.target}', sorry."
)
