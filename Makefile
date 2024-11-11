GRADLE = ./gradlew
DOCKER = docker

# Define the Gradle wrapper
.PHONY: clean
clean: ## Clean project
	$(GRADLE) clean

.PHONY: build
build: ## Build project
	$(GRADLE) build

.PHONY: buildContainer
buildContainer: ## Package project to a container
	$(GRADLE) jibDockerBuild

.PHONY: test
test: ## Run test for project
	$(GRADLE) test

.PHONY: run
run: ## Run the project
	$(GRADLE) bootRun

.PHONY: runContainer
runContainer: buildContainer ## Run containerised project with an H2-database
	@$(eval VERSION=$(shell ./gradlew -q printVersion))
	$(DOCKER) run -it --rm -p 8080:8080 deltabeer:$(VERSION) --spring.profiles.active=local-h2

.PHONY: tasks
tasks: ## Display a list of available Gradle tasks
	$(GRADLE) tasks

.PHONY: help
help:
	@echo "Makefile for a Deltabeer"
	@echo ""
	@echo "Usage:"
	@echo "  make <target>"
	@echo ""
	@echo "Targets and their descriptions:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-20s\033[0m %s\n", $$1, $$2}'
