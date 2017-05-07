export class TreeElement {

    private static sortByNameAndType() {
        return (a: TreeElement, b: TreeElement) => {
            if (a.isFile === b.isFile) {
                return a.name.localeCompare(b.name);
            }

            return a.isFile ? 1 : -1;
        };
    }

    public readonly id: string;
    public readonly key: string;
    public measures: MeasureList;

    public name: string;
    public readonly path: string;

    public parent?: TreeElement;
    public readonly children: TreeElement[];

    public readonly isFile: boolean;

    public constructor(id: string, key: string, measures: MeasureList, name: string, path: string, isFile: boolean,
                       parent?: TreeElement) {
        this.id = id;
        this.key = key;
        this.measures = measures;
        this.name = name;
        this.path = path;
        this.isFile = isFile;
        this.parent = parent;
        this.children = [];
    }

    public getSortedChilds(): TreeElement[] {
        return this.children.sort(TreeElement.sortByNameAndType());
    }

}