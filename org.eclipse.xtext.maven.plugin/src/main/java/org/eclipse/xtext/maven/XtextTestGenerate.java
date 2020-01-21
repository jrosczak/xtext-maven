package org.eclipse.xtext.maven;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Sets.newLinkedHashSet;

import java.util.List;
import java.util.Set;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(name = "testGenerate", defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
public class XtextTestGenerate extends AbstractXtextGeneratorMojo {

	/**
	 * Project classpath.
	 */
	@Parameter(defaultValue = "${project.testClasspathElements}", readonly = true, required = true)
	private List<String> classpathElements;

	public Set<String> getClasspathElements() {
		Set<String> classpathElementSet = newLinkedHashSet();
		classpathElementSet.addAll(this.classpathElements);
		classpathElementSet.remove(project.getBuild().getTestOutputDirectory());
		return newLinkedHashSet(filter(classpathElementSet, emptyStringFilter()));
	}

}
