package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.solver.impl.NaivePyramidSolver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class NaivePyramidSolverTest extends BasePyramidSolverTest {

    @Before
    public void setUp() {
        solver = createSolver();
    }

    @Override
    protected PyramidSolver createSolver() {
        return new NaivePyramidSolver();
    }

    @Test
    @Ignore("This test is disabled because it takes too long to run")
    public void solverSurvivesLargeData() {
        super.solverSurvivesLargeData();
    }
}
