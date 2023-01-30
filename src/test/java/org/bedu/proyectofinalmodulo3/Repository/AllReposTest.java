package org.bedu.proyectofinalmodulo3.Repository;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({IProductoFunkoRepositoryTest.class, IUsuarioRepositoryTest.class})
public class AllReposTest {
}
