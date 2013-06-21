/*
 * SoftViz3d Sonar plugin
 * Copyright (C) 2013 Stefan Rinderle
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package de.rinderle.softviz3d.layout;

import att.grappa.Graph;
import de.rinderle.softviz3d.layout.calc.AbsolutePositionCalculator;
import de.rinderle.softviz3d.layout.calc.LayoutVisitor;
import de.rinderle.softviz3d.layout.dot.DotExcecutorException;
import de.rinderle.softviz3d.layout.helper.GraphDebugPrinter;
import de.rinderle.softviz3d.layout.helper.LayeredLayoutElement;
import de.rinderle.softviz3d.layout.interfaces.SourceObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Layout {

  // private static final Logger LOGGER = LoggerFactory
  // .getLogger(Layout.class);

  private LayoutVisitor visitor;

  public Layout(LayoutVisitor visitor) {
    this.visitor = visitor;
  }

  public Map<Integer, Graph> startLayout(SourceObject source) throws DotExcecutorException {
    // STEP 1 ---

    // last output element could be used to start absolutepositioncalc
    this.accept(source);
    Map<Integer, Graph> resultGraphs = this.visitor.getResultingGraphList();
    // ----------

    // debug output
    StringBuilder builder = new StringBuilder();
    GraphDebugPrinter.printGraphsWithoutAbsolutePosition(builder, resultGraphs);

    startAbsolutePositioning(source, resultGraphs, builder);

    GraphDebugPrinter.printGraphsWithAbsolutePosition(builder, resultGraphs);

    // LOGGER.info(builder.toString());

    return resultGraphs;
  }

  private Map<Integer, Graph> startAbsolutePositioning(SourceObject source,
      Map<Integer, Graph> resultGraphs, StringBuilder builder) {
    // NEXT STEP HERE
    AbsolutePositionCalculator calc = new AbsolutePositionCalculator(resultGraphs);
    calc.calculate(source);
    // ---

    // debug result graphs after positioning graphs
    GraphDebugPrinter.printGraphsWithAbsolutePosition(builder, resultGraphs);

    return resultGraphs;
  }

  /**
   * Bottom up calculation of layout layers.
   * 
   * Public because of unit testing access.
   */
  public LayeredLayoutElement accept(SourceObject source)
      throws DotExcecutorException {
    ArrayList<LayeredLayoutElement> layerElements = new ArrayList<LayeredLayoutElement>();

    List<? extends SourceObject> childrenNodes = source.getChildrenNodes();
    for (SourceObject node : childrenNodes) {
      layerElements.add(this.accept(node));
    }

    List<? extends SourceObject> childrenLeaves = source
        .getChildrenLeaves();
    for (SourceObject leaf : childrenLeaves) {
      layerElements.add(visitor.visitFile(leaf));
    }

    LayeredLayoutElement layer = visitor.visitNode(source, layerElements);

    return layer;
  }

}
