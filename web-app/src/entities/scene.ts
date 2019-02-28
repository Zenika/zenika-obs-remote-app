export class Scene {
  name: string;
  sources: any[];

  constructor(name: string, sources: any[]) {
    this.name = name;
    this.sources = sources;
  }

  public getName(): string {
    return this.name;
  }

  public setName(name: string) {
    this.name = name;
  }

  public getSources(): any[] {
    return this.sources;
  }

  public addSource(source: any) {
    this.sources.push(source);
  }
}
