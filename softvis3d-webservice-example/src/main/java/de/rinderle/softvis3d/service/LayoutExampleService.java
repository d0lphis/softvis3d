/*
 * softvis3d-webservice-example
 * Copyright (C) 2015 Stefan Rinderle
 * stefan@rinderle.info
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
package de.rinderle.softvis3d.service;

import com.google.inject.Inject;
import de.rinderle.softvis3d.base.VisualizationAdditionalInfos;
import de.rinderle.softvis3d.base.VisualizationProcessor;
import de.rinderle.softvis3d.base.VisualizationSettings;
import de.rinderle.softvis3d.base.domain.LayoutViewType;
import de.rinderle.softvis3d.base.domain.MinMaxValue;
import de.rinderle.softvis3d.base.domain.SnapshotTreeResult;
import de.rinderle.softvis3d.base.domain.graph.ResultPlatform;
import de.rinderle.softvis3d.base.domain.tree.RootTreeNode;
import de.rinderle.softvis3d.base.domain.tree.TreeNode;
import de.rinderle.softvis3d.base.domain.tree.TreeNodeType;
import de.rinderle.softvis3d.base.domain.tree.ValueTreeNode;
import de.rinderle.softvis3d.base.layout.dot.DotExecutorException;
import java.util.Map;

public class LayoutExampleService {

  @Inject
  private VisualizationProcessor visualizationProcessor;

  public Map<Integer, ResultPlatform> getExampleResult() throws Exception {
    final VisualizationSettings settings = new VisualizationSettings();
    final SnapshotTreeResult snapshotTreeResult = createExampleSnapshotTreeResult(1);
    final VisualizationAdditionalInfos additionalInfos = createExampleAdditionalInfos();
    try {
      return visualizationProcessor
          .visualize(LayoutViewType.CITY, settings, snapshotTreeResult, additionalInfos);
    } catch (final DotExecutorException e) {
      throw new Exception(e.getMessage(), e);
    }
  }

  private SnapshotTreeResult createExampleSnapshotTreeResult(final int rootId) {
    final RootTreeNode result = new RootTreeNode(rootId);

    final TreeNode node2 = new ValueTreeNode(2, result, 1, TreeNodeType.TREE, "2", 1.3, 1.5, 2);
    final TreeNode node3 = new ValueTreeNode(3, result, 1, TreeNodeType.TREE, "3", 1.7, 1.8, 1);
    result.addChildrenNode("2", node2);
    result.addChildrenNode("3", node3);

    return new SnapshotTreeResult(result);
  }

  private VisualizationAdditionalInfos createExampleAdditionalInfos() {
    final MinMaxValue minMaxFootprint = new MinMaxValue(0, 3);
    final MinMaxValue minMaxHeight = new MinMaxValue(0, 3);
    final MinMaxValue minMaxColor = new MinMaxValue(0, 3);
    final int dependencyCount = 0;

    return new VisualizationAdditionalInfos(minMaxFootprint, minMaxHeight, minMaxColor, dependencyCount);
  }


  public SnapshotTreeResult getExampleResultTree() {
    return createExampleSnapshotTreeResult(1);
  }
}