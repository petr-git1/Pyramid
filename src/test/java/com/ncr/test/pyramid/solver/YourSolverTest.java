package com.ncr.test.pyramid.solver;

import com.ncr.test.pyramid.solver.impl.YourSolver;
import org.junit.Before;

public class YourSolverTest extends BasePyramidSolverTest {

    @Before
    public void setUp() {
        solver = createSolver();
    }

    @Override
    protected PyramidSolver createSolver() {
        return new YourSolver();
    }
}
