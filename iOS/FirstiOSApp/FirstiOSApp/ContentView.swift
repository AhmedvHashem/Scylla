//
//  ContentView.swift
//  FirstiOSApp
//
//  Created by Ahmed Hashem on 17/12/2024.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text("Hello, world!").bold()
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
